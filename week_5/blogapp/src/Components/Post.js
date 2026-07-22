import React, { Component } from 'react';

class Post extends Component {
    render() {
        const { title, content, author } = this.props;
        return (
            <div className="post">
                <h2>{title}</h2>
                <p>{content}</p>
                <p><em>By {author}</em></p>
            </div>
        );
    }
}

export default Post;
