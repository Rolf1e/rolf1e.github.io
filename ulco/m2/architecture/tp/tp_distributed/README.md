
Launch dockers with `docker compose up -d`.

Connect to postgres with 
```shell
psql postgresql://admin:password@localhost:5432/clickhouse_pg_db
```

Connect to clickhouse with
```shell
clickhouse-client --host localhost  --port 9000
```
