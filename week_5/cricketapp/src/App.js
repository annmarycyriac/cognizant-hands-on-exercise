import React, { useState } from 'react';
import './App.css';
import PlayerList from './Components/PlayerList';

const allPlayers = [
    { name: 'Virat Kohli', score: 85 },
    { name: 'Rohit Sharma', score: 45 },
    { name: 'Jasprit Bumrah', score: 12 },
    { name: 'KL Rahul', score: 92 },
    { name: 'Hardik Pandya', score: 55 },
    { name: 'Ravindra Jadeja', score: 30 },
    { name: 'Shubman Gill', score: 78 },
    { name: 'Mohammed Shami', score: 8 },
    { name: 'Rishabh Pant', score: 62 },
    { name: 'Suryakumar Yadav', score: 95 },
    { name: 'Axar Patel', score: 40 }
];

const T20players = allPlayers.slice(0, 5);
const RanjiTrophyPlayers = allPlayers.slice(5);
const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

function App() {
    const [showMerged, setShowMerged] = useState(false);

    const filteredLowScores = allPlayers.filter(player => player.score < 70);

    const oddPlayers = allPlayers.filter((_, index) => index % 2 !== 0);
    const evenPlayers = allPlayers.filter((_, index) => index % 2 === 0);

    return (
        <div className="App">
            <h1>Cricket App</h1>

            <h2>All Players (map)</h2>
            <ul>
                {allPlayers.map((player, index) => (
                    <li key={index}>{player.name} - {player.score}</li>
                ))}
            </ul>

            <h2>Players with Score Below 70 (filter)</h2>
            <ul>
                {filteredLowScores.map((player, index) => (
                    <li key={index}>{player.name} - {player.score}</li>
                ))}
            </ul>

            <h2>Odd Team Players (destructuring)</h2>
            <PlayerList players={oddPlayers} title="Odd Team" />

            <h2>Even Team Players (destructuring)</h2>
            <PlayerList players={evenPlayers} title="Even Team" />

            <button onClick={() => setShowMerged(!showMerged)}>
                {showMerged ? 'Hide' : 'Show'} Merged Players
            </button>

            {showMerged && (
                <div>
                    <h2>Merged T20 + Ranji Trophy Players</h2>
                    <ul>
                        {mergedPlayers.map((player, index) => (
                            <li key={index}>{player.name} - {player.score}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default App;
