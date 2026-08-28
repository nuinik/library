package hr.algebra.plantapp.service;

import hr.algebra.plantapp.dto.PlantRequest;
import hr.algebra.plantapp.dto.PlantResponse;
import hr.algebra.plantapp.entity.Plant;
import hr.algebra.plantapp.entity.PlantType;
import hr.algebra.plantapp.exception.ResourceNotFoundException;
import hr.algebra.plantapp.repository.PlantRepository;
import hr.algebra.plantapp.repository.PlantTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantService {
    private final PlantRepository plantRepository;
    private final PlantTypeRepository plantTypeRepository;

    public PlantService(PlantRepository plantRepository, PlantTypeRepository plantTypeRepository) {
        this.plantRepository = plantRepository;
        this.plantTypeRepository = plantTypeRepository;
    }

    public List<PlantResponse> findAll() {
        return plantRepository.findAll().stream().map(this::toResponse).toList();
    }

    public PlantResponse findById(Long id) {
        return toResponse(findPlant(id));
    }

    public PlantResponse create(PlantRequest request) {
        PlantType plantType = findPlantType(request.typeId());
        Plant plant = new Plant(request.name(), request.price(), plantType);
        Plant savedPlant = plantRepository.save(plant);
        return toResponse(savedPlant);
    }

    public PlantResponse update(Long id, PlantRequest request) {
        Plant plant = findPlant(id);
        PlantType plantType = findPlantType(request.typeId());
        plant.setName(request.name());
        plant.setPrice(request.price());
        plant.setType(plantType);
        Plant updatedPlant = plantRepository.save(plant);
        return toResponse(updatedPlant);
    }

    public void delete(Long id) {
        Plant plant = findPlant(id);
        plantRepository.delete(plant);
    }

    private Plant findPlant(Long id) {
        return plantRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Biljka s ID-em " + id + " ne postoji."));
    }

    private PlantType findPlantType(Long id) {
        return plantTypeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tip biljke s ID-em " + id + " ne postoji."));
    }

    private PlantResponse toResponse(Plant plant) {
        return new PlantResponse(plant.getId(), plant.getName(), plant.getPrice(),
                plant.getType().getId(), plant.getType().getName());
    }
}
