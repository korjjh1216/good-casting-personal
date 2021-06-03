import React, { useEffect, useRef, useState } from 'react'
import styled from 'styled-components'
import DayPickerInput from 'react-day-picker/DayPickerInput'
import 'react-day-picker/lib/style.css'
import { useDispatch } from 'react-redux'
import SearchBtnComponent from '../Core/SearchBtn'

const DatePickerStyled = styled.div`
    display: flex;
    align-items: center;
    height: 45px;
    .DayPickerInput {
        display: block;
    }
    .DayPickerInput-Overlay {
        top: 4px;
    }
    .DayPicker-Day {
        padding: 0.35rem 0.5rem;
    }
    .DayPicker-Day--today {
        color: ${({ theme }) => theme.colors.primary};
    }
    .DayPicker-Day--selected:not(.DayPicker-Day--disabled):not(.DayPicker-Day--outside) {
        background-color: ${({ theme }) => theme.colors.primary};
        color: #fff;
    }
    .DayPicker-Day--selected:not(.DayPicker-Day--disabled):not(.DayPicker-Day--outside):hover {
        background-color: ${({ theme }) => theme.colors.primary};
    }
`

const DatePickerComponent = ({ isRangeSearch }) => {
    const today = new Date()
    const [startDate, setStartDate] = useState(new Date())
    const [endDate, setEndDate] = useState(new Date())

    const dateToString = (date) => {
        return (
            date.getFullYear() +
            '-' +
            (date.getMonth() + 1).toString().padStart(2, '0') +
            '-' +
            date
                .getDate()
                .toString()
                .padStart(2, '0')
        )
    }

    const dispatch = useDispatch()

    useEffect(() => {
        dateToString(startDate)
        dateToString(endDate)
    }, [startDate, endDate])

    return (
        <>
            <DatePickerStyled>
                <DayPickerInput
                    dayPickerProps={{ disabledDays: { before: today } }}
                    value={startDate}
                    onDayChange={(day) => {
                        setStartDate(day)
                    }}
                />
            </DatePickerStyled>
            {isRangeSearch && (
                <>
                    <h6 className="font-size-4 font-weight-semibold mb-6 w-75">종료일</h6>
                    <DatePickerStyled>
                        <DayPickerInput
                            dayPickerProps={{
                                disabledDays: { before: startDate },
                            }}
                            value={endDate}
                            onDayChange={(day) => {
                                setEndDate(day)
                            }}
                        />
                    </DatePickerStyled>
                    <SearchBtnComponent data={{ startDate, endDate }} text={'기간 설정'} className="btn btn-primary line-height-reset h-50 w-50 text-uppercase" />
                </>
            )}
        </>
    )
}

export default DatePickerComponent
