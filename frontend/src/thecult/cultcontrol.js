import serverdict from './serverdict'

export default {
    current_lang : "CULT",

    getTranslationOf(word){
        if(this.cultIsLang())
            return serverdict.cultDict[word];
        else if(this.engIsLang())
            return serverdict.engDict[word];
        else
            throw "This word is not found!";
    },
    changeLang(lang) {
        if(lang === "CULT")
            this.current_lang = lang;
        else if(lang === "ENG")
            this.current_lang = lang;
        return;
    },
    cultIsLang(){
        return "CULT" === this.current_lang;
    },
    engIsLang(){
        return "ENG" === this.current_lang;
    }
}