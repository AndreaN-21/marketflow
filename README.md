# MarketFlow 

MarketFlow is a portfolio project with a microservices architecture:

| `api-gateway` | 8080 | Routing, rate limiting, JWT passthrough |
| `user-service` | 8081 | User profiles, synced from Supabase Auth |
| `product-service` | 8082 | Product catalog, Redis cache, Elasticsearch |
| `order-service` | 8083 | Order lifecycle, Kafka producer |
| `notification-service` | 8084 | Kafka consumer, email via AWS SES |
| `ai-service` | 8085 | Product descriptions via Claude API |

