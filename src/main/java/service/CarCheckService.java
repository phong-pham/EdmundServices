package service;

import model.CarCheck;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by phongpham on 1/4/16.
 */
public interface CarCheckService {

    public List<CarCheck> doCarCheck(int year, String make, String model) throws MalformedURLException, IOException;
}
