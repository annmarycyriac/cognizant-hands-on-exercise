import React from 'react';

function PlayerList({ players, title }) {
    return (
        <div>
            <h3>{title}</h3>
            <ul>
                {players.map((player, index) => (
                    <li key={index}>{player.name}: {player.score} runs</li>
                ))}
            </ul>
        </div>
    );
}

export default PlayerList;
