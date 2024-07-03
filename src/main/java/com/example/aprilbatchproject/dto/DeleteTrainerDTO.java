package com.example.aprilbatchproject.dto;

public class DeleteTrainerDTO {

	    private String trainerName;
	    private String email;
	    private String phone;
	    private String specialization;
	    

	    public DeleteTrainerDTO(String trainerName,String email,String phone,String specialization ) {
	        this.trainerName = trainerName;
	        this.email=email;
	        this.phone=phone;
	        this.specialization=specialization;
	    }

	    // Getter and setter
	    public String gettrainerName() {
	        return trainerName;
	    }

	    public void settrainerName(String trainerName) {
	        this.trainerName = trainerName;
	    }

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getSpecialization() {
			return specialization;
		}

		public void setSpecialization(String specialization) {
			this.specialization = specialization;
		}
	}
