package com.csc340.localharvest_hub.producebox;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csc340.localharvest_hub.farm.Farm;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProduceBoxService {
    private final ProduceBoxRepository produceBoxRepository;

    public ProduceBox createProduceBox(ProduceBox produceBox) {
        return produceBoxRepository.save(produceBox);
    }

    public ProduceBox updateProduceBox(Long id, ProduceBox boxDetails) {
        ProduceBox box = produceBoxRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produce box not found"));
        
        box.setName(boxDetails.getName());
        box.setDescription(boxDetails.getDescription());
        box.setPrice(boxDetails.getPrice());
        box.setAvailable(boxDetails.isAvailable());
        
        return produceBoxRepository.save(box);
    }

    public void deleteProduceBox(Long id) {
        if (!produceBoxRepository.existsById(id)) {
            throw new EntityNotFoundException("Produce box not found");
        }
        produceBoxRepository.deleteById(id);
    }

    public ProduceBox getProduceBoxById(Long id) {
        return produceBoxRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produce box not found"));
    }

    public List<ProduceBox> getAllProduceBoxes() {
        return produceBoxRepository.findAll();
    }

    public List<ProduceBox> getAvailableProduceBoxes() {
        return produceBoxRepository.findByAvailable(true);
    }

    public List<ProduceBox> getProduceBoxesByFarm(Farm farm) {
        return produceBoxRepository.findByFarmAndAvailable(farm, true);
    }
}