import * as React from 'react';
import './hero-grid.scss';
import PropTypes from 'prop-types';
//import { CityCard } from '../../../common/CityCard/CityCard';
import { CenterCroppedImage } from '../../../common/CenterCroppedImage/CenterCroppedImage';

export class HeroGrid extends React.Component {
    constructor(props) {
        super(props);
	    
	    this.state = {
            dateDay: '',
            dateMonth: '',
            dateDate: '',
            zoomIn: [false, false, false],
        };      
    }

    componentWillMount() {
        this.makeDate();
        this.renderHeroGridCell = this.renderHeroGridCell.bind(this);
    }
    
    makeDate() {
        const now = new Date();
        var date = now.getDate();
        var month = this.mapMonth(now.getMonth() + 1);
        var day = this.mapDay(now.getDay());   
        
        this.setState({
            dateDay: day,
            dateMonth: month,
            dateDate: date,
        })
    }

    mapMonth(month){
        switch(month) 
        { 
        case 1:return "一";  
        case 2:return "二"; 
        case 3:return "三"; 
        case 4:return "四"; 
        case 5:return "五"; 
        case 6:return "六"; 
        case 7:return "七"; 
        case 8:return "八"; 
        case 9:return "九"; 
        case 10:return "十"; 
        case 11:return "十一";
        case 12:return "十二";
        default:return "" ;
        } 
    }

    mapDay(day) {
        switch(day) 
        { 
        case 0:return "星期日"; 
        case 1:return "星期一"; 
        case 2:return "星期二"; 
        case 3:return "星期三"; 
        case 4:return "星期四"; 
        case 5:return "星期五"; 
        case 6:return "星期六"; 
        default:return "" ;
        } 
    }

    renderHeroGridCell(index) {
        const post = this.props.headlinePosts[index];
        
        return (
            <a 
                className='hero-grid-cell' 
                href={`/a/${post.id}`} 
                onMouseEnter={ () => {
                    const z = this.state.zoomIn;
                    z[index] = true;
                    this.setState({zoomIn: z});
                } }
                onMouseLeave={ () => {
                    const z = this.state.zoomIn;
                    z[index] = false;
                    this.setState({zoomIn: z});
                } }
            >
                <CenterCroppedImage 
                    className='hero-grid-cell-img' 
                    src={ post.titleImageSrc }
                    isLazyload={ true }
                    preloadColor='#009966'
                    zoomIn={ this.state.zoomIn[index] }
                />
                <span className='hero-grid-cell-title'>{ post.title }</span>
            </a>
        )      
    }

	render() {
		return (
			<div className='hero-grid'>
				<div className='hero-grid-l' />
                <div className='hero-grid-r'>
                    {/* <div className='hero-grid-city' >
                        <CityCard />
                        <p className='hero-grid-city-name'>圣彼得堡</p>
                    </div> */}
                    {/* <div className='hero-grid-time' >
                        <span className='month'>{ this.state.dateMonth }月</span>
                        <span className='date'>{ this.state.dateDate }</span>
                        <div className='day'>
                            <span>{ this.state.dateDay }</span>
                        </div>
                    </div> */}
                    <div className='col-l'>
                        {this.renderHeroGridCell(0)}
                    </div>
                    <div className='col-r'>
                        <div className='row'>
                            {this.renderHeroGridCell(1)}
                        </div>
                        <div className='row'>
                            {this.renderHeroGridCell(2)}
                        </div>
                    </div>
                </div>
				
			</div>
		);
  	}
}

HeroGrid.propTypes = {
    headlinePosts: PropTypes.arrayOf(PropTypes.object)
};

HeroGrid.defaultProps = {
};