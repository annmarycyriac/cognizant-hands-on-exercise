import React, { useState } from 'react';
import './App.css';
import Header from './Components/Header';
import Article from './Components/Article';
import Footer from './Components/Footer';

const articles = [
    { id: 1, title: 'Getting Started with React', content: 'React is a JavaScript library for building UIs.', isPublished: true },
    { id: 2, title: 'Conditional Rendering', content: 'You can conditionally render components using if, && or ternary.', isPublished: true },
    { id: 3, title: 'Draft Article', content: 'This is not published yet.', isPublished: false }
];

function App() {
    const [showPublished, setShowPublished] = useState(true);

    const filteredArticles = showPublished
        ? articles.filter(a => a.isPublished)
        : articles;

    return (
        <div className="App">
            <Header />
            <button onClick={() => setShowPublished(!showPublished)}>
                {showPublished ? 'Show All' : 'Show Published'}
            </button>
            {filteredArticles.length > 0 ? (
                filteredArticles.map(article => (
                    <Article key={article.id} {...article} />
                ))
            ) : (
                <p>No articles to display.</p>
            )}
            <Footer />
        </div>
    );
}

export default App;
