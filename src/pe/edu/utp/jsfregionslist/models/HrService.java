package pe.edu.utp.jsfregionslist.models;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HrService {
    private Connection connection;
    private RegionsEntity regionsEntity;
    private CountriesEntity countriesEntity;

    private Connection getConnection() {
        if (connection==null){
            //Metodo de clase, para que realiza la busqueda del datasource
            try {
                connection = ((DataSource)InitialContext.doLookup("jdbc/MySQLDataSource"))
                        .getConnection();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected RegionsEntity getRegionsEntity() {
        if(getConnection() != null) {
            if(regionsEntity == null) {
                regionsEntity = new RegionsEntity();
                regionsEntity.setConnection(getConnection());
            }
        }
        return regionsEntity;
    }

    protected CountriesEntity getCountriesEntity() {
        if(getConnection() != null) {
            if(countriesEntity == null) {
                countriesEntity = new CountriesEntity();
                countriesEntity.setConnection(getConnection());
            }
        }
        return countriesEntity;
    }

    public List<Region> findAllRegions() {
        return getRegionsEntity() != null ?
                getRegionsEntity().findAll() : null;
    }

    public List<Country> findAllCountries() {
        return (getCountriesEntity() != null &&
                getRegionsEntity() != null) ?
                getCountriesEntity().findAll(getRegionsEntity()) : null;

    }
    public Region findRegionById(int id) {
        return getRegionsEntity() != null ?
                getRegionsEntity().findById(id) : null;
    }

    public Region findRegionByName(String name) {
        return getRegionsEntity() != null ?
                getRegionsEntity().findByName(name) : null;
    }

    public Region createRegion(String name) {
        return getRegionsEntity() != null ?
                getRegionsEntity().create(name) : null;
    }

    public boolean deleteRegion(int id) {
        return getRegionsEntity() != null ?
                getRegionsEntity().delete(id) : false;
    }

    public boolean updateRegion(Region region) {
        return getRegionsEntity() != null ?
                getRegionsEntity().update(region) : false;
    }
}
