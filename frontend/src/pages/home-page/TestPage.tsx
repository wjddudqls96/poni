import React from 'react'
import { NavigateFunction, useNavigate } from 'react-router-dom'

const TestPage: React.FC = () => {

    let navigate: NavigateFunction = useNavigate();

    const hadleNavigate = () => {
        navigate("/2");
    }

  return (
    <div>
        {/* TestPage
        <button onClick={hadleNavigate}>Go to About Page</button> */}
    </div>
  )
}

export default TestPage