1. Create a mock Table Business in DB for this Project (install mySQL), then use query in createMockUpDB to query table
2. Connect to DB through Java code and retrieve Records from Table
Install dependencies:
- mysql-connector-java
3. Convert results of Table into Java object with the help of POJO class implement
4. Convert Java objects into json file using jackson API
Install dependencies:
- com.fasterxml.jackson.core > jackson-core
- com.fasterxml.jackson.core > jackson-databind
- com.fasterxml.jackson.core > jackson-annotations
5. Merge all json files into an only one json file, using json.simple dependency
Install dependencies:
- com.google.code.gson > gson
- Apache Commons Text for remove backslash in json file