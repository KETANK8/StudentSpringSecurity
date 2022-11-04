package com.studentswagger.modeldto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class StudentsDTO {

	@NotNull(message = "Id cannot be null!!")
	private int stId;
	@NotNull(message = "First Name cannot be null!!")
	private String stFirstName;
	@NotNull(message = "Last Name cannot be null!!")
	private String stLastName;
	@NotNull(message = "User Name cannot be null!!")
	private String stUserName;
	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9_@#]{8,30}", message = "\nPassword can be AlphaNumeric and Atleast have 8 Characters.\n(Use atleast 1 Upper Case, 1 Lower Case, 1 Number)")
	private String stPassWord;
	@NotNull(message = "Domain cannot be null!!")
	private String stDomain;
	@Email
	private String stEmail;
	private String stContact;
	private int stAge;

}
