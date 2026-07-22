import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
    const { Name, School, Total, goal } = props;
    const average = Total / 4;
    return (
        <div className="calculate-score">
            <h2>Student Score Card</h2>
            <p><strong>Name:</strong> {Name}</p>
            <p><strong>School:</strong> {School}</p>
            <p><strong>Total:</strong> {Total}</p>
            <p><strong>Goal:</strong> {goal}</p>
            <p><strong>Average:</strong> {average.toFixed(2)}</p>
        </div>
    );
}

export default CalculateScore;
