package entity;

import Utilities.AssertUtility;
import apis.libraryapis.GetCoursesPojoResponse;

public class LibraryApiEntity {
    private GetCoursesPojoResponse actualResponse;
    private GetCoursesPojoResponse expectedResponse;

    public LibraryApiEntity(GetCoursesPojoResponse actualResponse, GetCoursesPojoResponse expectedResponse) {
        this.actualResponse = actualResponse;
        this.expectedResponse = expectedResponse;
    }

    public void verify() {
        AssertUtility.assertEquals(actualResponse.getInstructor(), expectedResponse.getInstructor(), "instructor");
        AssertUtility.assertEquals(actualResponse.getUrl(), expectedResponse.getUrl(), "url");
        AssertUtility.assertEquals(actualResponse.getServices(), expectedResponse.getServices(), "services");
        AssertUtility.assertEquals(actualResponse.getExpertise(), expectedResponse.getExpertise(), "expertise");

        for (int i = 0; i < actualResponse.getCourses().getWebAutomation().size(); i++) {
            AssertUtility.assertEquals(actualResponse.getCourses().getWebAutomation().get(i).getCourseTitle(), expectedResponse.getCourses().getWebAutomation().get(i).getCourseTitle(), "courses:webAutomation:courseTitle");
            AssertUtility.assertEquals(actualResponse.getCourses().getWebAutomation().get(i).getPrice(), expectedResponse.getCourses().getWebAutomation().get(i).getPrice(), "courses:webAutomation:price");
        }

        for (int i = 0; i < actualResponse.getCourses().getApi().size(); i++) {
            AssertUtility.assertEquals(actualResponse.getCourses().getApi().get(i).getCourseTitle(), expectedResponse.getCourses().getApi().get(i).getCourseTitle(), "courses:webAutomation:courseTitle");
            AssertUtility.assertEquals(actualResponse.getCourses().getApi().get(i).getPrice(), expectedResponse.getCourses().getApi().get(i).getPrice(), "courses:webAutomation:price");
        }

        for (int i = 0; i < actualResponse.getCourses().getMobile().size(); i++) {
            AssertUtility.assertEquals(actualResponse.getCourses().getMobile().get(i).getCourseTitle(), expectedResponse.getCourses().getMobile().get(i).getCourseTitle(), "courses:webAutomation:courseTitle");
            AssertUtility.assertEquals(actualResponse.getCourses().getMobile().get(i).getPrice(), expectedResponse.getCourses().getMobile().get(i).getPrice(), "courses:webAutomation:price");
        }
        AssertUtility.assertEquals(actualResponse.getLinkedIn(), expectedResponse.getLinkedIn(), "linkedIn");
    }
}
