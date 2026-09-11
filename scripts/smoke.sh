#!/bin/bash
set -e

cd "$(dirname "$0")/.."

PORT=${PORT:-8080}
BASE_URL="http://localhost:${PORT}"

OK=0
FALHA=0

echo "🔍 Executando Smoke Tests para: $BASE_URL"

# Função auxiliar de teste
test_route() {
    local nome="$1"
    local endpoint="$2"
    local expected_text="$3"

    echo -n "  - Testando $nome ($endpoint)... "
    response=$(curl -s -w "\n%{http_code}" "$BASE_URL$endpoint" || echo "ERRO 000")
    http_code=$(echo "$response" | tail -n1)
    body=$(echo "$response" | sed '$d')

    if [ "$http_code" -eq 200 ] && echo "$body" | grep -q "$expected_text"; then
        echo "✅ OK (HTTP $http_code)"
        OK=$((OK + 1))
    else
        echo "❌ FALHA (HTTP $http_code)"
        echo "    Resposta: $body"
        FALHA=$((FALHA + 1))
    fi
}

test_route "Aplicação Viva" "/health" '"status":"UP"'
test_route "Banco de Dados" "/health/db" '"status":"UP"'
test_route "Página Web Inicial" "/" "Sistema Semeando"

echo "----------------------------------------"
echo "Resultado: OK $OK · FALHA $FALHA"
echo "----------------------------------------"

if [ $FALHA -gt 0 ]; then
    echo "❌ O smoke test falhou!"
    exit 1
else
    echo "🎉 Todos os smoke tests passaram com sucesso!"
    exit 0
fi
