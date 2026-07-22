import React from 'react';

function Article({ title, content, isPublished }) {
    if (!isPublished) {
        return null;
    }
    return (
        <div className="article">
            <h3>{title}</h3>
            <p>{content}</p>
        </div>
    );
}

export default Article;
