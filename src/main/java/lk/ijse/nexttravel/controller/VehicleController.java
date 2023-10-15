package lk.ijse.nexttravel.controller;

import lk.ijse.nexttravel.dto.VehicleDTO;
import lk.ijse.nexttravel.service.VehicleService;
import lk.ijse.nexttravel.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vehicle")
public class VehicleController{

    private final VehicleService vehicleService;

    //handle vehicle post request
    @PostMapping("/save")
    public Mono<ResponseUtil> saveVehicleData(@RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.saveVehicle(vehicleDTO).map(savedVehicle ->
                new ResponseUtil(200,"Vehicle Saved",null));
    }

    //handle vehicle by Id Get request
    @GetMapping("{vehicleId}")
    public Mono<ResponseUtil> getVehicleData(@PathVariable int vehicleId) {
        return vehicleService.getVehicle(vehicleId).map(vehicle->
                new ResponseUtil(200,vehicleId+"Vehicle Retrieved",vehicle));
    }

    //handle getAll vehicle request
    @GetMapping("/getAll")
    public Flux<ResponseUtil> getAllVehiclesData() {
        return vehicleService.getAllVehicles().map(vehicles ->
                new ResponseUtil(200,"Get All vehicles",vehicles));
    }

    //handle vehicle update request
    @PutMapping("{vehicleId}")
    public Mono<ResponseUtil> updateVehicleData(@RequestBody VehicleDTO vehicleDTO,@PathVariable int vehicleId) {
        return null;
    }

    //handle vehicle delete by id request
    @DeleteMapping("{vehicleId}")
    public Mono<ResponseUtil> deleteVehicleData(@PathVariable int vehicleId) {
        return vehicleService.deleteVehicle(vehicleId).map(deletedVehicle ->
                new ResponseUtil(200,vehicleId+" Deleted Success",null));
    }
}