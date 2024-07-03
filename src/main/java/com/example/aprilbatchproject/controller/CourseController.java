package com.example.aprilbatchproject.controller;

import com.example.aprilbatchproject.entity.Courses;
import com.example.aprilbatchproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.example.aprilbatchproject.dto.CourseDTO;
import com.example.aprilbatchproject.dto.DeleteCourseDTO;
import com.example.aprilbatchproject.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;

	@PostMapping("/create_course")
	public ResponseEntity<ApiResponse<Courses>> createCourse(@RequestBody CourseDTO courses) throws Exception {
		Courses course = courseService.createCourse(courses);
		return ResponseEntity.ok(new ApiResponse<>(
				true,
				"Course Data Save Successfully",
				course));
	}

    @GetMapping
    public List<CourseDTO> getCourse() {
        return courseService.getCourses();
    }

	@GetMapping("/getAllCoursesName")
	public ResponseEntity<ApiResponse<List<CourseDTO>>> getCoursesNames() {
//		return courseService.getAllCourseNames();
		List<CourseDTO> courseNames = courseService.getAllCourseNames();
		ApiResponse<List<CourseDTO>> response = new ApiResponse<>(true, "Courses fetched successfully", courseNames);
		return  ResponseEntity.ok(response);
	}

	@GetMapping("/getCourseById/{id}")

	public ResponseEntity<ApiResponse<CourseDTO>> getCourseById(@PathVariable Long id) {
		Optional<Courses> course = courseService.getCourseById(id);

		if (!course.isPresent()) {
			ApiResponse<CourseDTO> response = new ApiResponse<>(false, "Course not found", null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		CourseDTO courseDTO = courseService.convertToCourseDTO(course.get());

		ApiResponse<CourseDTO> response = new ApiResponse<>(true, "Courses fetched Successfully using id", courseDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	//getendpoint course information based on name
	@GetMapping("/coursedetailsByName")

	public ResponseEntity<ApiResponse<CourseDTO>> getCoursedeatilsByName(@RequestParam String name) {
		try {
			CourseDTO coursedetails = courseService.getCoursedetaileByName(name);
			ApiResponse<CourseDTO> response = new ApiResponse<>(true, "Course details retrived for this name", coursedetails);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage(), null));
		}
	}

	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity<ApiResponse<DeleteCourseDTO>> deleteCourseById(@PathVariable Long id){
		DeleteCourseDTO deleteCourseDTO = courseService.deleteCourse(id);

		ApiResponse<DeleteCourseDTO> courseResponce = new ApiResponse<>(true, "Course Deleted Successfully", deleteCourseDTO);
		return ResponseEntity.ok(courseResponce);
	}
}
