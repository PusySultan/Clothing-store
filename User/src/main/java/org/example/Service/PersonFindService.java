package org.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class PersonFindService
{
    @Autowired
    private RestTemplate restTemplate;

    @Value("${clothing.url}")
    String clothingUrl;

    /// Фильтрация по типу
    public ResponseEntity<?> findClothingByType(String type)
    {
        if(PersonAuntService.auntPerson != null)
        {
            String url = UriComponentsBuilder
                    .fromHttpUrl(String.format("http://%s/find", clothingUrl))
                    .queryParam("type", type)
                    .toUriString();

            String answer = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(answer);
        }

        return ResponseEntity.badRequest().body("Войдите в систему");
    }

    /// Фильтрация по бренду
    public ResponseEntity<?> findClothingByBrand(String brand)
    {
        if(PersonAuntService.auntPerson != null)
        {
            String url = UriComponentsBuilder
                    .fromHttpUrl(String.format("http://%s/find", clothingUrl))
                    .queryParam("brand", brand)
                    .toUriString();

            String answer = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(answer);
        }

        return ResponseEntity.badRequest().body("Войдите в систему");
    }

    /// Фильтрация по максимальной стоимости
    public ResponseEntity<?> findClothingByMaxCost(double maxCost)
    {
        if(PersonAuntService.auntPerson != null)
        {
            String url = UriComponentsBuilder
                    .fromHttpUrl(String.format("http://%s/find", clothingUrl))
                    .queryParam("maxCost", maxCost)
                    .toUriString();

            String answer = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(answer);
        }

        return ResponseEntity.badRequest().body("Войдите в систему");
    }

    /// Фильтрация по минимальной стоимости
    public ResponseEntity<?> findClothingByMinCost(double minCost)
    {
        if(PersonAuntService.auntPerson != null)
        {
            String url = UriComponentsBuilder
                    .fromHttpUrl(String.format("http://%s/find", clothingUrl))
                    .queryParam("minCost", minCost)
                    .toUriString();

            String answer = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(answer);
        }

        return ResponseEntity.badRequest().body("Войдите в систему");
    }
}
