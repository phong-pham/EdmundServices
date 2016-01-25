import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.CarCheckService;

/**
 * Created by phongpham on 1/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring-service-context.xml"})
public class CarCheckTest {

    @Autowired
    private CarCheckService carCheckService;

    @Test
    public void testDoCarCheck(){
        try{
            carCheckService.doCarCheck(-1, "lexus", "rx350");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
