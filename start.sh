#!/bin/bash
set -e

# Garante execução a partir do diretório raiz do projeto
cd "$(dirname "$0")"

echo "=================================================="
echo "🚀 Iniciando Sistema Semeando – Gestão Escolar"
echo "=================================================="

# 1. Carregar variáveis do .env se existir
if [ -f .env ]; then
    export $(grep -v '^#' .env | xargs)
fi

PORT=${PORT:-8080}
DB_HOST=${DB_HOST:-localhost}
DB_PORT=${DB_PORT:-3306}

# 2. Verificar/iniciar contêiner MySQL se Docker estiver presente
if which docker >/dev/null 2>&1; then
    if ! docker ps --filter "name=semeando-mysql" --format '{{.Names}}' | grep -q "semeando-mysql"; then
        echo "📦 Iniciando contêiner MySQL 'semeando-mysql'..."
        if docker ps -a --filter "name=semeando-mysql" --format '{{.Names}}' | grep -q "semeando-mysql"; then
            docker start semeando-mysql >/dev/null
        else
            docker run -d --name semeando-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=semeando_db -p 3306:3306 mysql:8.0 >/dev/null
        fi
        echo "⏳ Aguardando banco de dados ficar pronto..."
        sleep 5
    fi
fi

echo "☕ Compilando e iniciando aplicação Spring Boot na porta $PORT..."
# Inicia a aplicação em background para permitir healthcheck se rodado interativamente, ou executa diretamente
mvn spring-boot:run
