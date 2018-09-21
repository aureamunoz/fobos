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

###Prerequisites
The logfile is supposed to exist in _/tmp/log_

###How to use :

    git clone https://github.com/aureamunoz/fobos.git

    cd fobos

    mvn clean package

The program is called in command line:

    java -jar target/fobos-{version}.jar
or
    mvn spring-boot:run

* By default, the generated files containing the statisques of connections will be available in _/tmp_
* By default, the stats are generated once every hour

* The hosts to monitore for stats are specified by keys bellow:
    * **fobos.stats.to.hostname** *(Ex : fobos.stats.to.hostname=quark)*
    * **fobos.stats.from.hostname** *(Ex : fobos.stats.from.hostname=lilac)*
    * **fobos.stats.cron** *(Ex :fobos.stats.cron=0/5 * * * * ?)*


** Mejoras/Notas
Implementar la lectura del fichero en tiempo real: in progress. No consigo leer las líneas añadidas al fichero una
vez el programa está corriendo. He probado varias estrategias y no funcionan, sigo mirando (por curiosidad) qué es lo
que bloquea.
Implementar filtro sobre las fechas de los mensajes.
Optimizar la cache usando otra persistente, tipo redis.
Afinar el tamaño de la cache en función de la velocidad a la que llegan los datos de conexiones entre hosts.
Para optimizar los accesos y la memoria usada podría reflexionar más sobre el tipo de datos almacenado en la cache,
utilizando alguna forma más compacta. Ademas usando librerias como fastutil se consiguen accesos más rápidos.
Añadir test unitarios/integración
Añadir log4j para tener trazas sobre el funcionamiento de la aplicación.
Más javadoc
Gestion de errores
Tests, tests, tests
