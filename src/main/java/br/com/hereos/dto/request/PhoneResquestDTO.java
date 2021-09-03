package br.com.hereos.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.hereos.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneResquestDTO {

	private Long id;
	
	private PhoneType type;
	
	@NotEmpty
	@Size(min = 13, max=14)
	private String number;
}
