import React, { Component } from 'react';
import './App.css';
import CurrencyConverter from './Components/CurrencyConverter';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = { counter: 0 };
    }

    increment = () => {
        this.setState({ counter: this.state.counter + 1 });
        alert('Hello! Counter incremented.');
    };

    decrement = () => {
        this.setState({ counter: this.state.counter - 1 });
    };

    sayWelcome = (msg) => {
        alert(msg);
    };

    handlePress = () => {
        alert('I was clicked');
    };

    render() {
        return (
            <div className="App">
                <h1>Event Examples App</h1>

                <h2>Counter: {this.state.counter}</h2>
                <button onClick={this.increment}>Increment</button>
                <button onClick={this.decrement}>Decrement</button>

                <br /><br />
                <button onClick={() => alert('Hello static message!')}>Say Hello</button>
                <button onClick={() => this.sayWelcome('welcome')}>Say Welcome</button>
                <button onClick={this.handlePress}>OnPress</button>

                <hr />
                <CurrencyConverter />
            </div>
        );
    }
}

export default App;
