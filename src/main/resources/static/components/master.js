class Master extends React.Component{
    constructor(props){
        super(props);
        this.state={masters:this.props.masters, schedule:false};
        this.renderMasterList=this.renderMasterList.bind(this);
        this.renderList=this.renderList.bind(this);
        this.renderWeek=this.renderWeek.bind(this);
        this.renderData=this.renderData.bind(this);
        this.onClick=this.onClick.bind(this);
    }
    onClick(e){
        alert(e.target.dataset.id);
        this.setState({schedule:true});
    }
    renderList(item,i){
        return <img src="img/photo.jpg" onClick={this.onClick} data-id={item.id}  />;
    }
    renderMasterList(){
        return <div className="master_photo">
                   {this.state.masters.map(this.renderList)}
               </div>;
    }
    renderWeek(){
        if(this.state.schedule)
           return <Schedule />;
    }
    renderData(){
        return <div>
                   {this.renderMasterList()}
                   {this.renderWeek()}
               </div>;
    }


    render(){
    console.log(this.state.masters);
        return this.renderData();
    }
}