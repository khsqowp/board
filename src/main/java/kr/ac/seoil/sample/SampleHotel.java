package kr.ac.seoil.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
@RequiredArgsConstructor
public class SampleHotel {
    @NonNull
    private Chef chef;
    private Restaurant restaurant;

	/* public SampleHotel(Chef chef, Restaurant restaurant) {
		this.chef = chef;
	} */

}
