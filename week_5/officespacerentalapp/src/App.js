import React from 'react';
import './App.css';

const officeSpaces = [
    { id: 1, name: 'CoWork Hub', rent: 45000, address: '123 MG Road, Bangalore' },
    { id: 2, name: 'BizCenter', rent: 75000, address: '456 Brigade Road, Bangalore' },
    { id: 3, name: 'Workspace Pro', rent: 55000, address: '789 Koramangala, Bangalore' },
    { id: 4, name: 'Executive Suite', rent: 90000, address: '321 Indiranagar, Bangalore' }
];

function App() {
    return (
        <div className="App">
            <h1>Office Space Rental App</h1>
            <img src="https://via.placeholder.com/600x200?text=Office+Space" alt="Office Space" />

            {officeSpaces.map(space => (
                <div key={space.id} className="office-card">
                    <h3>{space.name}</h3>
                    <p><strong>Address:</strong> {space.address}</p>
                    <p style={{ color: space.rent < 60000 ? 'red' : 'green', fontWeight: 'bold' }}>
                        <strong>Rent:</strong> ₹{space.rent.toLocaleString()}
                    </p>
                </div>
            ))}
        </div>
    );
}

export default App;
