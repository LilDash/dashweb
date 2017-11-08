import * as React from 'react';
import PropTypes from 'prop-types';
import { Header } from '../Header/Header';
import { Footer } from '../Footer/Footer';
import { Hero } from './subcomponents/Hero/Hero';
import { HighlightNav } from './subcomponents/HighlightNav/HighlightNav';
import { PostList } from './subcomponents/PostList/PostList';
import { SectionBracket } from './subcomponents/SectionBracket/SectionBracket';
import '../../styles/default.scss';
import './home-page.scss';

export default class HomePage extends React.Component {
  render() {
    return (
    	<div className='home-page'>
    		<Header />
            <Hero {...this.props.hero} />
    		<div className='home-page-body'>    			
                <HighlightNav {...this.props.highlightNav} />
                <SectionBracket name='最新发布' direction='down' />
                <PostList {...this.props.postList} />
    		</div>
    		<Footer />
    	</div>
    );
  }
};

HomePage.propTypes = {
    hero: PropTypes.object.isRequired,
    highlightNav: PropTypes.object.isRequired,
    postList: PropTypes.object.isRequired,
};
