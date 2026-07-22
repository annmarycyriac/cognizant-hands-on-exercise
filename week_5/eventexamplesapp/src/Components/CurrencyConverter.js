import React, { Component } from 'react';

class CurrencyConverter extends Component {
    constructor(props) {
        super(props);
        this.state = {
            inr: 0,
            euro: 0
        };
    }

    handleSubmit = (e) => {
        e.preventDefault();
        const rate = 0.011;
        this.setState({ euro: this.state.inr * rate });
    };

    render() {
        return (
            <div>
                <h3>Currency Converter (INR to Euro)</h3>
                <form onSubmit={this.handleSubmit}>
                    <input
                        type="number"
                        value={this.state.inr}
                        onChange={(e) => this.setState({ inr: e.target.value })}
                        placeholder="Enter amount in INR"
                    />
                    <button type="submit">Convert</button>
                </form>
                <p>Euro: €{this.state.euro.toFixed(2)}</p>
            </div>
        );
    }
}

export default CurrencyConverter;
