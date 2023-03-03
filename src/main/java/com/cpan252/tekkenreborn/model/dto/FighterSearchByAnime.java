package com.cpan252.tekkenreborn.model.dto;

import com.cpan252.tekkenreborn.model.Fighter.Anime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FighterSearchByAnime {
    private Anime animeFrom;
}
