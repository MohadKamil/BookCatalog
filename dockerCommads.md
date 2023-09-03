### Create network

```bash
docker network create catalog-network
```

### Run postgres SQL

```bash
docker run --net catalog-network --name polargres -e POSTGRES_DB=polar_catalog_db -e POSTGRES_USER=user -e POSTGRES_PASSWORD=Password -p 5432:5432 -d postgres
```

### Prepare Catalog Jar file

```bash
./gradlew clear bootJar
```


### Build Catalog Image

```bash
 docker build -t catalog:1.0.0 .
```

### Run Catalog Image

```bash
docker run --rm --name catalog --net catalog-network -e SPRING_DATASOURCE_URL="jdbc:postgresql://polargres:5432/polar_catalog_db?user=user&password=Password" -e SPRING_PROFILES_ACTIVE=TestData -p 9001:9001 catalog:1.0.0
```


### Alternative

OCI compliant image can be build using Cloud native build pack project

```bash
pack build catalog:pack1.0.0 --builder paketobuildpacks/builder-jammy-tiny
```