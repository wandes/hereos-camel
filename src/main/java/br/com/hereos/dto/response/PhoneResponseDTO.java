package br.com.hereos.dto.response;

import java.io.Serializable;

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
public class PhoneResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private PhoneType type;
	
	private String number;
}
