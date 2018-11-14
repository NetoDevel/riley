package br.com.rileyframework.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RileyTemplateEngine {

    private static final String REGEX_PATTERN_HVAR = ("(\\{(\\w+)\\})+");
    private Map<String, Object> viewAttributes;

    public String format(String htmlPuroAntesDoServerSide) {
        if (htmlPuroAntesDoServerSide == null) throw new TemplateEngineException("html can not be null");
        if (htmlPuroAntesDoServerSide.isEmpty()) throw new TemplateEngineException("html can not be empty");

        List<String> hvars = hVars(htmlPuroAntesDoServerSide);
        HashMap<String, Object> convertHvarEmValores = hVarToValue(hvars);

        String htmlResult = new String();
        for (String hvar : convertHvarEmValores.keySet()) {
            htmlPuroAntesDoServerSide = htmlPuroAntesDoServerSide.replace(hvar, (String) convertHvarEmValores.get(hvar));
            htmlResult = htmlPuroAntesDoServerSide;
        }
        return htmlResult;
    }

    public RileyTemplateEngine modelAndView(Map<String, Object> viewAttributes) {
        if (viewAttributes == null) throw new TemplateEngineException("view model can not be null");
        this.viewAttributes = viewAttributes;
        return this;
    }

    public List<String> hVars(String html) {
        List<String> hVarsResult = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_PATTERN_HVAR);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            hVarsResult.add(matcher.group());
        }
        return hVarsResult;
    }

    public HashMap<String, Object> hVarToValue(List<String> hVars) {
        HashMap<String, Object> hashToReturned = new HashMap<>();
        hashToReturned.put(hVars.get(0), viewAttributes.get(hVars.get(0)));
        return hashToReturned;
    }

}