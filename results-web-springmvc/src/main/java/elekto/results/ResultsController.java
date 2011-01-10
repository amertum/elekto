/**
 * elekto provides voting and elections tools for small and medium enterprise.
 * Copyright (C) 2010 http://www.elections-cedp.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package elekto.results;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 */
@Controller
@SessionAttributes(ResultsController.MODEL_ATTRIBUTE_RESULTS_INPUTS)
public class ResultsController {

    ResultsController(
            final ResultsProviderFactory resultsProviderFactory)
    {
        if (resultsProviderFactory == null) {
            throw new IllegalArgumentException("resultsProviderFactory must not be null");
        }

        this.resultsProviderFactory = resultsProviderFactory;
    }


    @ModelAttribute(MODEL_ATTRIBUTE_RESULTS_INPUTS)
    public ResultsInputsForm populateResultsInputsForm()
    {
        return new ResultsInputsForm();
    }


    @RequestMapping(value = "/calculate", method = GET)
    public ModelAndView main(
            @ModelAttribute(MODEL_ATTRIBUTE_RESULTS_INPUTS)
            final ResultsInputsForm resultsInputsForm,
            final BindingResult errors,
            final SessionStatus status)
        throws IOException
    {
        final ModelAndView modelAndView = new ModelAndView("calculate");

        new ResultInputsValidator().validate(resultsInputsForm, errors);
        if (errors.hasErrors()) {
            LOGGER.warn("errors: {}", errors);
        }
        if (resultsInputsForm.hasCachedElectionsModelData()) {
            final ResultsProvider resultsProvider = this.resultsProviderFactory.create(resultsInputsForm.getCachedElectionsModelData());
            modelAndView.addObject("resultsProvider", resultsProvider);

            // TODO with an DAO: resultsProvider.getCerfaDocument().save(outStream);
        }

        status.setComplete();

        return modelAndView;
    }


    /**
     * Upload the candidats model excel file.
     * 
     * @param resultsInputsForm
     * @param errors
     * 
     * @return the redirect view.
     * 
     * @throws IOException
     *         if unable to read the file.
     */
    @RequestMapping(value = "/calculate", method = POST)
    public ModelAndView calculateResults(
            @ModelAttribute(MODEL_ATTRIBUTE_RESULTS_INPUTS)
            final ResultsInputsForm resultsInputsForm,
            final BindingResult errors)
        throws IOException
    {
        new ResultInputsValidator().validate(resultsInputsForm, errors);
        if (errors.hasErrors()) {
            LOGGER.error("errors: {}", errors);

            return new ModelAndView("redirect:calculate");
        }

        resultsInputsForm.cacheElectionsModelData();

        return new ModelAndView("redirect:calculate");
    }


    @RequestMapping(value = "/help", method = GET)
    public ModelAndView modelHelp()
    {
        final ModelAndView modelAndView = new ModelAndView("help");

        return modelAndView;
    }


    @RequestMapping(value = "/elections-model.xls", method = GET)
    public ModelAndView modeleExcelFile(
            final HttpServletResponse response)
    {
        final ModelAndView modelAndView = new ModelAndView("classpathResourceView");
        modelAndView.addObject(ClasspathResourceView.CONTENT_TYPE_MODEL_KEY, "application/vnd.ms-excel");
        modelAndView.addObject(ClasspathResourceView.CLASSPATH_RESOURCE_MODEL_KEY, "/elections-template.xls");
        return modelAndView;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultsController.class);

    private final ResultsProviderFactory resultsProviderFactory;

    static final String MODEL_ATTRIBUTE_RESULTS_INPUTS = "resultsInputs";

    static final String FORM_ATTRIBUTE_ELECTIONS_MODEL_FILE = "electionsModelFile";

}
