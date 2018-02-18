package pe.edu.utp.jsfregionslist.beans;

import pe.edu.utp.jsfregionslist.models.HrService;
import pe.edu.utp.jsfregionslist.models.Region;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RegionsBean implements Serializable {
    private HrService service;

    public  RegionsBean(){
        service=new HrService();
    }
    public List<Region> getRegions(){
        return service.findAllRegions();
    }
}
