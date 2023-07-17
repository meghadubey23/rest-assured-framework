package apis.entity;

import apis.libraryapis.GetCoursesPojoResponse;
import org.testng.Assert;

public class LibraryApiEntity {
    private GetCoursesPojoResponse actualResponse;
    private GetCoursesPojoResponse expectedResponse;

    public LibraryApiEntity(GetCoursesPojoResponse actualResponse, GetCoursesPojoResponse expectedResponse) {
        this.actualResponse = actualResponse;
        this.expectedResponse = expectedResponse;
    }

    public void verify() {
        Assert.assertEquals(actualResponse.getInstructor(), expectedResponse.getInstructor());
        Assert.assertEquals(actualResponse.getUrl(), expectedResponse.getUrl());
        Assert.assertEquals(actualResponse.getServices(), expectedResponse.getServices());
        Assert.assertEquals(actualResponse.getExpertise(), expectedResponse.getExpertise());

        for (int i = 0; i < actualResponse.getCourses().getWebAutomation().size(); i++) {
            Assert.assertEquals(actualResponse.getCourses().getWebAutomation().get(i).getCourseTitle(), expectedResponse.getCourses().getWebAutomation().get(i).getCourseTitle());
            Assert.assertEquals(actualResponse.getCourses().getWebAutomation().get(i).getPrice(), expectedResponse.getCourses().getWebAutomation().get(i).getPrice());
        }

        for (int i = 0; i < actualResponse.getCourses().getApi().size(); i++) {
            Assert.assertEquals(actualResponse.getCourses().getApi().get(i).getCourseTitle(), expectedResponse.getCourses().getApi().get(i).getCourseTitle());
            Assert.assertEquals(actualResponse.getCourses().getApi().get(i).getPrice(), expectedResponse.getCourses().getApi().get(i).getPrice());
        }

        for (int i = 0; i < actualResponse.getCourses().getMobile().size(); i++) {
            Assert.assertEquals(actualResponse.getCourses().getMobile().get(i).getCourseTitle(), expectedResponse.getCourses().getMobile().get(i).getCourseTitle());
            Assert.assertEquals(actualResponse.getCourses().getMobile().get(i).getPrice(), expectedResponse.getCourses().getMobile().get(i).getPrice());
        }
        Assert.assertEquals(actualResponse.getLinkedIn(), expectedResponse.getLinkedIn());
    }
}
