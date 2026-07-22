import React, { useState } from 'react';
import './App.css';

const flights = [
    { id: 'AI101', from: 'Delhi', to: 'Mumbai', time: '06:00', price: 5000 },
    { id: 'SG202', from: 'Bangalore', to: 'Chennai', time: '08:30', price: 3500 },
    { id: '6E303', from: 'Mumbai', to: 'Delhi', time: '14:00', price: 4500 }
];

function GuestPage() {
    return (
        <div>
            <h2>Guest View - Browse Flights</h2>
            <p>Please log in to book tickets.</p>
            {flights.map(f => (
                <div key={f.id} className="flight-card">
                    <p><strong>{f.id}</strong>: {f.from} → {f.time} → {f.to} - ₹{f.price}</p>
                </div>
            ))}
        </div>
    );
}

function UserPage() {
    return (
        <div>
            <h2>User Dashboard - Book Tickets</h2>
            {flights.map(f => (
                <div key={f.id} className="flight-card">
                    <p><strong>{f.id}</strong>: {f.from} → {f.time} → {f.to} - ₹{f.price}</p>
                    <button>Book Now</button>
                </div>
            ))}
        </div>
    );
}

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    return (
        <div className="App">
            <h1>Ticket Booking App</h1>
            <div className="auth-bar">
                {isLoggedIn ? (
                    <button onClick={() => setIsLoggedIn(false)}>Logout</button>
                ) : (
                    <button onClick={() => setIsLoggedIn(true)}>Login</button>
                )}
            </div>
            {isLoggedIn ? <UserPage /> : <GuestPage />}
        </div>
    );
}

export default App;
