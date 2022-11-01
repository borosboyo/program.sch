import { configureStore } from '@reduxjs/toolkit'
import resourceGetterReducer from '../src/components/slice'

export default configureStore({
    reducer: {
        counter: resourceGetterReducer,
    },
})
