package org.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public ResponseEntity<?> checkClothingByType(String type)
    {
        if(PersonAuntService.auntPerson != null)
        {
            String url = UriComponentsBuilder
                    .fromHttpUrl(String.format("http://%s/find", clothingUrl))
                    .queryParam("type", type)
                    .toUriString();

            String answer = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok().body(answer);
        }

        return ResponseEntity.badRequest().body("Войдите в систему");
    }

    /// Фильтрация по бренду
    public ResponseEntity<?> checkClothingByBrand(String brand)
    {
        if(PersonAuntService.auntPerson != null)
        {
            return ResponseEntity.ok().body("Какойто ответ");
        }

        return ResponseEntity.badRequest().body("Войдите в систему");
    }

    /// Фильтрация по максимальной стоимости
    public ResponseEntity<?> checkClothingByMaxCost(double maxCost)
    {
        if(PersonAuntService.auntPerson != null)
        {
            return ResponseEntity.ok().body("Какойто ответ");
        }

        return ResponseEntity.badRequest().body("Войдите в систему");
    }

    /// Фильтрация по минимальной стоимости
    public ResponseEntity<?> checkClothingByMinCost(double minCost)
    {
        if(PersonAuntService.auntPerson != null)
        {
            return ResponseEntity.ok().body("Какойто ответ");
        }

        return ResponseEntity.badRequest().body("Войдите в систему");
    }
}
