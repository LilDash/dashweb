import * as React from 'react';
import PropTypes from 'prop-types';
import { Header } from '../Header/Header';
import { Footer } from '../Footer/Footer';
import { ArticleHero } from './subcomponents/ArticleHero/ArticleHero';
import { ArticleBody } from './subcomponents/ArticleBody/ArticleBody';


import '../../styles/default.scss';
import './article-page.scss';

export default class ArticlePage extends React.Component {

    constructor(props) {
        super(props);

      
    }

    componentDidMount() {
        
    }

  

    render() {
        return (
        	<div className='home-page'>
        		<Header />
                <ArticleHero {...this.props.articleHero} />
                <ArticleBody {...this.props.articleBody} />
        		<Footer />
        	</div>
        );
     }
};

ArticlePage.propTypes = {
    articleHero: PropTypes.object.isRequired,
    articleBody: PropTypes.object.isRequired
};
