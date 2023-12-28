import { createSlice } from '@reduxjs/toolkit'

export const slice = createSlice({
    name: 'resourceGetter',
    initialState: {
        value: '',
    },
    reducers: {
        getResource: (state, action) => {
            state.value = action.payload
        },
    },
})

export const { getResource } = slice.actions

export default slice.reducer
