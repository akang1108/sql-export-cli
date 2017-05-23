package info.akang.sqlexport.database

import javax.sql.DataSource

class DataSourceFactory {

    DataSource ds

    DataSourceFactory() {

        File f = new File("postgresql-42.1.1.jar")
        this.class.classLoader.addURL(f.toURL())

        ds = new org.apache.tomcat.jdbc.pool.DataSource()

        ds.driverClassName = ''
        ds.url = ''
        ds.username = ''
        ds.password = ''

        ds.defaultTransactionIsolation = 1
        ds.defaultReadOnly = true
        ds.defaultAutoCommit = false
        ds.initialSize = 1
        ds.minIdle = 1
        ds.maxIdle = 1
        ds.maxActive = 2
        ds.maxWait = -1
        ds.removeAbandonedTimeout = 300
        ds.testOnReturn = true
        ds.testOnBorrow = true
        ds.testWhileIdle = true
        ds.validationQuery = 'select 1'
    }

}
