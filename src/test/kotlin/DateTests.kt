package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

private class DateTests {

    @Test fun `Can get LocalDate to ISO String`() {
        assertThat(LocalDate.of(2000, 2, 29).format()).isEqualTo("2000-02-29")

        assertThat(LocalDate.of(2000, 2, 29).format("dd-MM-yyyy")).isEqualTo("29-02-2000")
    }

    @Test fun `Can get LocalDateTime to ISO String`() {
        //yyyy-mm-ddThh:mm:ss.ffffff
        assertThat(LocalDateTime.of(2000, 2, 29, 0, 0, 0, 0).format()).isEqualTo("2000-02-29T00:00:00")
    }

    @Test fun `Can convert ISO String to LocalDate`() {
        // iso format
        assertThat("2000-02-29".toLocalDate()).isEqualTo(LocalDate.of(2000, 2, 29))
        // custom format
        assertThat("29/02/2000".toLocalDate("dd/MM/yyyy")).isEqualTo(LocalDate.of(2000, 2, 29))
        // invalid format string
        assertThat("29/02/2000".toLocalDate("dd/MM/yyyy P")).isNull()
        // invalid date
        assertThat("test".toLocalDate()).isNull()
        assertThat("2000-02-30".toLocalDate()).isNull()
    }

    @Test fun `Can convert ISO String to LocalDateTime`() {
        //yyyy-mm-dd+hh:mm:ss
        assertThat("2000-02-29T11:12:13".toLocalDateTime()).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13))
        assertThat("2000-02-29T11:12:13.1".toLocalDateTime()).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13, 100000000))
        assertThat("2000-02-29T11:12:13.12".toLocalDateTime()).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13, 120000000))
        assertThat("2000-02-29T11:12:13.123".toLocalDateTime()).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13, 123000000))
        assertThat("2000-02-29T11:12:13.1234".toLocalDateTime()).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13, 123400000))
        assertThat("2000-02-29T11:12:13.12345".toLocalDateTime()).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13, 123450000))
        assertThat("2000-02-29T11:12:13.123456".toLocalDateTime()).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13, 123456000))

        // custom format
        assertThat("29-02-2000T11:12:13".toLocalDateTime("dd-MM-yyyy'T'HH:mm:ss")).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13))
        assertThat("29-02-2000T11:12:13.1234".toLocalDateTime("dd-MM-yyyy'T'HH:mm:ss.n")).isEqualTo(LocalDateTime.of(2000, 2, 29, 11, 12, 13, 1234))

        // date format
        assertThat("2000-02-29".toLocalDateTime()).isEqualTo(LocalDateTime.of(2000, 2, 29, 0,0,0))
        assertThat("29-02-2000".toLocalDateTime("dd-MM-yyyy")).isEqualTo(LocalDateTime.of(2000, 2, 29, 0,0,0))
        // invalid format string
        assertThat("29-02-2000".toLocalDateTime("dd-MM-yyyy P")).isNull()
        // invalid date string
        assertThat("2000-02-31".toLocalDateTime()).isNull()
        assertThat("test".toLocalDateTime()).isNull()
    }
}
