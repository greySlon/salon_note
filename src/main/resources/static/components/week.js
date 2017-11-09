class Week extends React.Component{
    constructor(props){
        super(props);
        this.state={week:this.props.week};
        this.renderDay=this.renderDay.bind(this);
    }
    days=["Понедельник","Вторник","Среда","Четверг","Пятница","Суббота","Воскресенье"];

    renderDay(item, i){
        return <Day day={item} schedule={this.state.week.schedule[i]} />;
    }

    render(){
    console.log("Week#render");
        if(this.state.week.schedule)
            return <div>
                      {this.days.map((item, i) => this.renderDay(item, i))}
                   </div>;
        else
            return "";
    }
}