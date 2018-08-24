package ryber;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class Hello {

    @Value("${spring.datasource.username}")
    private String value;

    @RequestMapping("/") @ResponseBody
    public String index() throws SQLException {
        return value;
    }
}
