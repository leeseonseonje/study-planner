package study.planner.app.studyplan

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import study.planner.app.studyplan.domain.PlanStatus.*
import study.planner.app.studyplan.domain.StudyPlan
import study.planner.app.studyplan.controller.dto.StudyPlanRegistrationRequest
import java.time.LocalDate

class StudyPlanTest {

    @Test
    fun currentFigureCalculateTest() {

        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000, LocalDate.now())
        val studyPlan = StudyPlan.of(r, null, LocalDate.now())

        studyPlan.currentFigureCalculate(100)

        assertThat(studyPlan.currentFigure).isEqualTo(100)

        studyPlan.currentFigureCalculate(400)

        assertThat(studyPlan.currentFigure).isEqualTo(400)

    }

    @Test
    fun currentProgress() {
        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                LocalDate.now())

        val studyPlan = StudyPlan.of(r, null, LocalDate.now())
        studyPlan.currentFigureCalculate(100)
        assertThat(studyPlan.progressConverter(studyPlan.currentFigure)).isEqualTo(10.0)
    }

    @Test
    fun averageProgress() {
        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                LocalDate.now())

        val studyPlan = StudyPlan.of(r, null, LocalDate.now())
        val averageProgress = studyPlan.progressConverter(10.0)
        assertThat(averageProgress).isEqualTo(1.0)
    }

    @Test
    fun dayProgress() {
        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                LocalDate.now())

        val studyPlan = StudyPlan.of(r, null, LocalDate.now())
        val dayProgress = studyPlan.progressConverter(500)
        assertThat(dayProgress).isEqualTo(50.0)
    }

    @Test
    fun restExpectCompleteDate() {
        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                LocalDate.of(9999, 7, 1))

        val studyPlan = StudyPlan.of(r, null, LocalDate.now())
        studyPlan.registrationDate = LocalDate.of(9999, 5, 22)

        assertThat(studyPlan.restExpectCompleteDate()).isEqualTo(40)
    }

    @Test
    fun expectCompleteDateExpired() {
        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                LocalDate.of(9999, 5, 1))

        val studyPlan = StudyPlan.of(r, null, LocalDate.now())
        studyPlan.registrationDate = LocalDate.of(9999, 5, 22)

        assertThat(studyPlan.restExpectCompleteDate()).isEqualTo(-21)
    }

    @Test
    fun notExpectCompleteDate() {
        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                null)

        val studyPlan = StudyPlan.of(r, null, LocalDate.now())
        studyPlan.registrationDate = LocalDate.of(9999, 5, 22)

        assertThat(studyPlan.expectCompleteDate).isNull()
        assertThat(studyPlan.restExpectCompleteDate()).isNull()
    }

    @Test
    fun t() {
        val now = LocalDate.now()
        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000,
                LocalDate.of(now.year.plus(1), now.month, now.dayOfMonth))
        val studyPlan = StudyPlan.of(r, null, LocalDate.now())

        println(studyPlan.restExpectCompleteDate())
    }

    @Test
    fun complete() {
        val r = StudyPlanRegistrationRequest(1L, "title", "content", 1000, LocalDate.now())
        val studyPlan = StudyPlan.of(r, null, LocalDate.now())

        studyPlan.currentFigureCalculate(1000)

        assertThat(studyPlan.status).isEqualTo(COMPLETE)
    }
}