# Fobos
**name**: fobos

**version**: 1.0.0-SNAPSHOT

**description**:

Tool that parse logfiles. Each line represents connection from a host (left) to another host (right) at a given time. The lines are roughly sorted by timestamp.
t input from a new logfile while it’s being written and run indefinitely.
For example:

    1366815793 quark garak
    1366815795 brunt quark
    1366815811 lilac garak

The script will output, once every hour:
a list of hostnames connected to a given (configurable) host during the last hour
a list of hostnames received connections from a given (configurable) host during the last hour the hostname that generated most connections in the last hour

###How to use :

    git clone https://github.com/aureamunoz/fobos.git

    cd fobos

    mvn clean package

The program is called in command line:

    java -jar target/fobos-{version}.jar

* By default, the file containing the connections lines will be read from _resources/connections-logfile_
* By default, the generated files containing the statisques of connections will be available in _/tmp_


** Mejoras
Se puede optimizar la cache usando otra persistente, tipo redis.
Para optimizar los accesos y la memoria usada podría reflexionar más sobre el tipo de datos almacenado en la cache,
utilizando alguna forma más compacta. Ademas usando librerias como fastutil se consiguen accesos más rápidos.
Añadir test unitarios/integración
Añadir log4j para tener trazas sobre el funcionamiento del programa.
Encapsular los servicios añadiendo interfaces.
Gestion de errores
