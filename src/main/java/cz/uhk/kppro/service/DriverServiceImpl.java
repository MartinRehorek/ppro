package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Driver;
import cz.uhk.kppro.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver getDriverById(long id) {
        return driverRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDriverById(long id) {
        Optional<Driver> Driver = driverRepository.findById(id);
        if (Driver.isPresent()) {
            driverRepository.delete(Driver.get());
        }
    }

    @Override
    public void saveDriver(Driver Driver) {
        driverRepository.save(Driver);
    }
}
