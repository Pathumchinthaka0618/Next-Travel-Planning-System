package lk.ijse.nexttravel.service.impl;

import lk.ijse.nexttravel.dto.VehicleDTO;
import lk.ijse.nexttravel.entity.Vehicle;
import lk.ijse.nexttravel.repository.VehicleRepository;
import lk.ijse.nexttravel.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final ModelMapper modelMapper;

    @Override
    public Mono<VehicleDTO> saveVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicleSave = modelMapper.map(vehicleDTO, Vehicle.class);
        return vehicleRepository.save(vehicleSave).map(savedVehicle ->
                modelMapper.map(savedVehicle,VehicleDTO.class));
    }

    @Override
    public Mono<VehicleDTO> getVehicle(int vehicleId) {
        Mono<Vehicle> byVehicleId = vehicleRepository.findByVehicleId(vehicleId);
        return byVehicleId.map(vehicle ->
                modelMapper.map(vehicle,VehicleDTO.class));
    }

    @Override
    public Flux<VehicleDTO> getAllVehicles() {
        Flux<Vehicle> allVehicles = vehicleRepository.findAll();
        return allVehicles.map(vehicles -> modelMapper.map(vehicles,VehicleDTO.class))
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<VehicleDTO> updateVehicle(VehicleDTO vehicleDTO, int vehicleId) {
        return null;
    }

    @Override
    public Mono<Void> deleteVehicle(int vehicleId) {
        return null;
    }
}
