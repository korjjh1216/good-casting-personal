import React, { useEffect, useState } from 'react'
import styled from 'styled-components'
import DayPickerInput from 'react-day-picker/DayPickerInput'
import 'react-day-picker/lib/style.css'
import { useDispatch } from 'react-redux'
import { hireList, setFfrom, setFto } from '../../state/reducer/hire.reducer'

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
        dispatch(setFfrom(dateToString(startDate)))
        dispatch(setFto(dateToString(endDate)))
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
                </>
            )}
        </>
    )
}

export default DatePickerComponent
