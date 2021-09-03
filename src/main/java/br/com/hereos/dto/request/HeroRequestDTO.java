package br.com.hereos.dto.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeroRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String name;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String heroName;

	@NotEmpty
	private String heroIdentification;

	private String description;

	private String birthDate;

	@Valid
	//@NotEmpty
	private List<PhoneResquestDTO> phones;

	@Override
	public String toString() {
		return "HeroDTO [id=" + id + ", name=" + name + ", heroName=" + heroName + ", heroIdentification="
				+ heroIdentification + ", description=" + description + ", birthDate=" + birthDate + ", phones="
				+ phones + "]";
	}
	
}
